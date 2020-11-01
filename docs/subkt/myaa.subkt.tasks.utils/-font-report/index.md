[subkt](../../index.md) / [myaa.subkt.tasks.utils](../index.md) / [FontReport](./index.md)

# FontReport

`class FontReport` [(source)](https://github.com/Myaamori/SubKt/blob/0.1.11/src/main/kotlin/myaa/subkt/tasks/utils/fontvalidator.kt#L194)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FontReport()` |

### Functions

| Name | Summary |
|---|---|
| [fauxBold](faux-bold.md) | `fun fauxBold(line: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, requestedWeight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, actualWeight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, font: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [fauxItalic](faux-italic.md) | `fun fauxItalic(line: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, font: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [italicMismatch](italic-mismatch.md) | `fun italicMismatch(line: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, font: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [limitLines](limit-lines.md) | `fun limitLines(lines: `[`Collection`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>, limit: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 10): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [missingFont](missing-font.md) | `fun missingFont(line: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, font: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [missingGlyphs](missing-glyphs.md) | `fun missingGlyphs(line: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, glyphs: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, font: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [printReport](print-report.md) | `fun printReport(onMissingFonts: `[`ErrorMode`](../../myaa.subkt.tasks/-error-mode/index.md)`, onFaux: `[`ErrorMode`](../../myaa.subkt.tasks/-error-mode/index.md)`, onStyleMismatch: `[`ErrorMode`](../../myaa.subkt.tasks/-error-mode/index.md)`, onMissingGlyphs: `[`ErrorMode`](../../myaa.subkt.tasks/-error-mode/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [weightMismatch](weight-mismatch.md) | `fun weightMismatch(line: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, requestedWeight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, actualWeight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, font: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
