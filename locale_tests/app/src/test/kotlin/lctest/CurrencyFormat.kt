package lctest

import org.junit.Test
import java.text.NumberFormat
import java.util.*

open class CurrencyFormatTestBase {
    enum class SymbolPos {
        Prefix,
        Suffix,
        Undefined,
    }

    data class SymbolInfo(
        val locale: Locale,
        val currency: Currency,
        val symbol: String,
        val position: SymbolPos,
        val example: String,
    )

    fun getSymbolInfo(currency: Currency, locale: Locale): SymbolInfo {
        val decimal = 12345678.990
        val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
        currencyFormatter.currency = currency
        val symbol = currency.getSymbol(locale)
        val formatText = currencyFormatter.format(decimal)

        val symbolPos =
            if (formatText.startsWith(symbol))
                SymbolPos.Prefix
            else if (formatText.endsWith(symbol))
                SymbolPos.Suffix
            else
                SymbolPos.Undefined

        return SymbolInfo(
            locale = locale,
            currency = currency,
            symbol = symbol,
            position = symbolPos,
            example = formatText,
        )
    }
}

class CurrencyFormatTest : CurrencyFormatTestBase() {
    @Test
    fun listAllSymbols() {
        val allLocales = Locale.getAvailableLocales()
        val allCurrencies = Currency.getAvailableCurrencies()

        // JPY, USD, ....
        val currencyList = allCurrencies.sortedBy { it.currencyCode }

        val map = mutableMapOf<String, MutableList<SymbolInfo>>()
        for (currency in currencyList) {
            for (locale in allLocales) {
                val iso4127 = currency.currencyCode
                if (map[iso4127] == null)
                    map[iso4127] = mutableListOf()

                map[iso4127]?.add(
                    getSymbolInfo(currency, locale)
                )
            }
        }

        for (pair in map.toSortedMap()) {
            val code = pair.key
            val symbols = pair.value
                .distinctBy { it.example }
                .sortedBy { it.locale.toString() }
            for (info in symbols) {
                print(code)
                print('\t')
                print(info.symbol)
                print('\t')
                print(info.position.toString().padEnd(10))
                print('\t')
                print(info.example)
                print('\t')
                print(info.locale)
                print('\n')
            }
        }

    }
}

class CurrencyCheckSymbolPositionByLang_WarnThisIsHeavy : CurrencyFormatTestBase() {
    @Test
    fun checkPosition() {
        val allLocales = Locale.getAvailableLocales()
        val allCurrencies = Currency.getAvailableCurrencies()

        // JPY, USD, ....
        val currencyList = allCurrencies.sortedBy { it.currencyCode }

        val map = mutableMapOf<String, MutableList<SymbolInfo>>()
        for (currency in currencyList) {
            for (locale in allLocales) {
                val lang = locale.language

                if (map[lang] == null)
                    map[lang] = mutableListOf()

                map[lang]?.add(
                    getSymbolInfo(currency, locale)
                )
            }
        }


        for (pair in map.toSortedMap()) {
            val lang = pair.key
            val symbols = pair.value
                .distinctBy { it.example }
                .sortedBy { it.locale.toString() }

            for (info in symbols) {
                print(lang)
                print('\t')
                print(info.currency.currencyCode)
                print('\t')
                print(info.symbol)
                print('\t')
                print(info.position.toString().padEnd(10))
                print('\t')
                print(info.example)
                print('\t')
                print(info.locale)
                print('\n')
            }
        }
    }
}

class CurrencyCheckSymbolPositionByLang_Summary : CurrencyFormatTestBase() {
    @Test
    fun checkPosition() {
        val allLocales = Locale.getAvailableLocales()
        val allCurrencies = Currency.getAvailableCurrencies()

        // JPY, USD, ....
        val currencyList = allCurrencies.sortedBy { it.currencyCode }

        val map = mutableMapOf<String, MutableList<SymbolInfo>>()
        for (currency in currencyList) {
            for (locale in allLocales) {
                val lang = locale.language

                if (locale.country.isBlank())
                    continue

                if (map[lang] == null)
                    map[lang] = mutableListOf()

                map[lang]?.add(
                    getSymbolInfo(currency, locale)
                )
            }
        }


        for (pair in map.toSortedMap()) {
            val lang = pair.key
            val symbols = pair.value
                .distinctBy { it.position }
                .sortedBy { it.locale.toString() }

            for (info in symbols) {
                print(lang)
                print('\t')
                print(info.currency.currencyCode)
                print('\t')
                print(info.symbol)
                print('\t')
                print(info.position.toString().padEnd(10))
                print('\t')
                print(info.example)
                print('\n')
            }
        }
    }
}