package myaa.subkt.tasks.mkvmerge

import com.google.gson.Gson
import java.io.File
import java.io.Serializable
import java.lang.RuntimeException
import java.math.BigInteger
import java.util.concurrent.TimeUnit

data class MkvAttachmentProperty(
        val uid: BigInteger? = null
) : Serializable

data class MkvAttachment(
        val file_name: String,
        val id: Long,
        val properties: MkvAttachmentProperty,
        val size: Long,
        val content_type: String? = null,
        val description: String? = null,
        val type: String? = null
) : Serializable

data class MkvChapter(
        val num_entries: Long
) : Serializable

data class MkvGlobalTag(
        val num_entries: Long
) : Serializable

data class MkvTrackTag(
        val num_entries: Long,
        val track_id: Long
) : Serializable

data class MkvContainerPropertiesProgram(
        val program_number: Long? = null,
        val service_name: String? = null,
        val service_provider: String? = null
) : Serializable

data class MkvContainerProperties(
        val container_type: Long? = null,
        val date_local: String? = null,
        val date_utc: String? = null,
        val duration: Long? = null,
        val is_providing_timestamps: Boolean? = null,
        val muxing_application: String? = null,
        val next_segment_uid: String? = null,
        val other_file: List<String>? = null,
        val playlist: Boolean? = null,
        val playlist_chapters: Long? = null,
        val playlist_duration: Long? = null,
        val playlist_file: List<String>? = null,
        val playlist_size: Long? = null,
        val previous_segment_uid: String? = null,
        val programs: List<MkvContainerPropertiesProgram>? = null,
        val segment_uid: String? = null,
        val title: String? = null,
        val writing_application: String? = null
) : Serializable

data class MkvContainer(
        val properties: MkvContainerProperties? = null,
        val recognized: Boolean,
        val supported: Boolean,
        val type: String? = null
) : Serializable

data class MkvTrackProperties(
    val aac_is_sbr: String? = null,
    val audio_bits_per_sample: Long? = null,
    val audio_channels: Long? = null,
    val audio_sampling_frequency: Long? = null,
    val codec_delay: Long? = null,
    val codec_id: String? = null,
    val codec_name: String? = null,
    val codec_private_data: String? = null,
    val codec_private_length: Long? = null,
    val content_encoding_algorithms: String? = null,
    val default_duration: Long? = null,
    val default_track: Boolean? = null,
    val display_dimensions: String? = null,
    val display_unit: Long? = null,
    val enabled_track: Boolean? = null,
    val encoding: String? = null,
    val forced_track: Boolean? = null,
    val language: String? = null,
    val minimum_timestamp: Long? = null,
    val multiplexed_tracks: List<Long>? = null,
    val number: Long? = null,
    val packetizer: String? = null,
    val pixel_dimensions: String? = null,
    val program_number: Long? = null,
    val stereo_mode: Long? = null,
    val stream_id: Long? = null,
    val sub_stream_id: Long? = null,
    val tag_artist: String? = null,
    val tag_bitsps: String? = null,
    val tag_bps: String? = null,
    val tag_fps: String? = null,
    val tag_title: String? = null,
    val teletext_page: Long? = null,
    val text_subtitles: Boolean? = null,
    val track_name: String? = null,
    val uid: BigInteger? = null
) : Serializable

data class MkvTrack(
        val codec: String,
        val id: Long,
        val type: String,
        val properties: MkvTrackProperties? = null
) : Serializable

data class MkvInfo(
        val attachments: List<MkvAttachment>? = null,
        val chapters: List<MkvChapter>? = null,
        val container: MkvContainer? = null,
        val errors: List<String>,
        val file_name: String? = null,
        val global_tags: List<MkvGlobalTag>? = null,
        val identification_format_version: Long? = null,
        val track_tags: List<MkvTrackTag>? = null,
        val tracks: List<MkvTrack>? = null,
        val warnings: List<String>
) : Serializable

/**
 * Reads metadata such as track and codec information from a Matroska-compatible
 * file using `mkvmerge`.
 *
 * @param file The file to read metadata from.
 * @param mkvmerge The path to the `mkvmerge` binary, if not present in your PATH.
 * @return An [MkvInfo] instance containing the information read from the file.
 * @throws RuntimeException If `mkvmerge` failed.
 */
fun getMkvInfo(file: File, mkvmerge: String = "mkvmerge"): MkvInfo {
    val filePath = file.absolutePath
    val proc = ProcessBuilder(mkvmerge, "-J", filePath)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .start()
    if (!proc.waitFor(180, TimeUnit.SECONDS)) {
        proc.destroyForcibly()
        throw RuntimeException("mkvmerge -J command timed out for file $filePath")
    }

    val parsed = proc.inputStream.reader().use {
        Gson().fromJson(it, MkvInfo::class.java)
    }

    if (proc.exitValue() != 0) {
        val errors = parsed.errors.joinToString("")
        throw RuntimeException("mkvmerge -J command failed for file $filePath: $errors")
    }

    return parsed
}
