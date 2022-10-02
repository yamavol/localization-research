package lctest

import org.junit.Test
import java.text.NumberFormat
import java.util.*

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



class CurrencySymbolsListGrouped {
    @Test
    fun listAllSymbolsGrouped() {
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

class CurrencyUsedCountry {

    @Test
    fun checkCountryToCurrencyMapping() {
        val allLocales = Locale.getAvailableLocales()

        fun mapper(lc: Locale): Pair<Locale, String> {
            return try {
                lc to Currency.getInstance(lc).currencyCode
            } catch (e: Exception) {
                lc to "XXX"
            }
        }

        val list = allLocales
            .filter { lc -> lc.country.isNotEmpty() }
            .map { lc -> mapper(lc) }
            .groupBy { it.first.country }
            .toList()
            .sortedBy { it.first }


        for (pair in list) {
            val countryCode = pair.first
            val items = pair.second

            val locales = items.map { item -> item.first }
            val currencies = items.map { item -> item.second }

            print(countryCode)
            print('\t')
            print_nothrow { Currency.getInstance(countryCode).getDisplayName(Locale.ENGLISH).padEnd(40) }
            print('\t')
            print(locales.size)
            print('\t')
            print(currencies.distinct().joinToString())
            print('\n')
        }
    }

    class CurrencyJDKvsISO4217 {
        val currencies = Currency.getAvailableCurrencies()

    }

}