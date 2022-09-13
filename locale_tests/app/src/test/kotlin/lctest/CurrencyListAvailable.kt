package lctest

import org.junit.Test
import java.text.NumberFormat
import java.util.*
import javax.swing.text.NumberFormatter

class CurrencyListAvailable {
    @Test
    fun listAvailableCurrencies() {
        val allCurrencies = Currency.getAvailableCurrencies()

        val list = allCurrencies.sortedBy { it.currencyCode }

        for (currency in list) {
            print(currency.currencyCode)
            print('\t')
            print(currency.getSymbol(Locale.ENGLISH).padEnd(10))
            print('\t')
            print(currency.symbol)
            print('\t')
            print(currency.getDisplayName(Locale.ENGLISH).padEnd(40))
            print('\t')
            print(currency.getDisplayName(Locale.JAPANESE))
            print('\n')
        }
    }
}

class CurrencyUsedInLocale {
    @Test
    fun listAvailableFromLocale() {
        val allLocales = Locale.getAvailableLocales()

        val list = allLocales
            .filter { lc -> lc.country.isNotEmpty() }
            .groupBy { lc -> try { Currency.getInstance(lc).currencyCode } catch (e: Exception) { "XXX" } }
            .toList()
            .sortedBy { it.first }


        for (pair in list) {
            val code = pair.first
            val locales = pair.second
            print(code)
            print('\t')
            print_nothrow { Currency.getInstance(code).getDisplayName(Locale.ENGLISH).padEnd(40) }
            print('\t')
            print(locales.size)
            print('\t')
            print(locales.map { lc -> lc.country }.distinct().joinToString())
            print('\n')
        }
    }
}

class CurrencySymbolsList {
    @Test
    fun listAllSymbols() {
        val allLocales = Locale.getAvailableLocales()
        val allCurrencies = Currency.getAvailableCurrencies()

        val currencyList = allCurrencies.sortedBy { it.currencyCode }

        val map = mutableMapOf<String, MutableList<String>>()
        for (currency in currencyList) {
            for (locale in allLocales) {
                val symbol = currency.getSymbol(locale)

                val iso4127 = currency.currencyCode
                if (map[iso4127] == null)
                    map[iso4127] = mutableListOf()

                map[iso4127]?.add(symbol)

                if (symbol.contains("щ")) {
                    print("${currency.currencyCode} symbol in locale: $locale is $symbol")
                }
            }
        }

        for (pair in map.toSortedMap()) {
            val code = pair.key
            val symbols = pair.value.distinct()
            print(code)
            print('\t')
            print(symbols.size)
            print('\t')
            print(symbols.joinToString())
            print('\n')
        }

    }
}

