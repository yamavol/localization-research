package lctest

import org.junit.Test
import java.text.NumberFormat
import java.util.Locale



class LocaleListAvailable {
    @Test
    fun listAvailableLocales() {
        val allLocales = Locale.getAvailableLocales()

        allLocales.sortWith { a, b -> a.toString().compareTo(b.toString()) }

        for (lc in allLocales) {
            print(lc.toString().padEnd(30))
            print('\t')
            print(lc.language.padEnd(3))
            print('\t')
            print_nothrow { lc.isO3Country.padEnd(3) }
            print('\t')
            print(lc.getDisplayLanguage(Locale.ENGLISH).padEnd(20))
            print('\t')
            print(lc.getDisplayCountry(Locale.ENGLISH))
            print('\n')

        }
    }
}

class LocaleListAvailableLanguages {
    @Test
    fun listAvailableLanguages() {
        val allLocales = Locale.getAvailableLocales()

        val list = allLocales
            .groupBy { it.language }
            .map { pair -> pair.key }
            .sorted()

        for (lang in list) {
            val lc = Locale(lang)
            print(lang)
            print('\t')
            print(lc.getDisplayLanguage(Locale.ENGLISH).padEnd(30))
            print('\t')
            print(lc.getDisplayLanguage(Locale.JAPANESE).padEnd(15))
            print('\n')
        }
    }
}

class LocaleListAvailableCountry {
    @Test
    fun listAvailableLanguages() {
        val allLocales = Locale.getAvailableLocales()

        val listlist = allLocales
            .filter { it.country.isNotEmpty() }
            .groupBy { it.country }
            .toList()
            .map { pair -> pair.second }
            .sortedBy { it.first().country }

        for (list in listlist) {
            val country = list.first().country
            print(country)
            print('\t')
            print(list.first().getDisplayCountry(Locale.ENGLISH).padEnd(30))
            print('\t')
            print(list.size)
            print('\t')
            print(list.joinToString { lc -> lc.language })
            print('\n')
        }
    }
}

class NumberFormatWithLocale {
    @Test
    fun printFormatted() {
        val allLocales = Locale.getAvailableLocales()
        allLocales.sortBy { it.language }

        val value = 12345678.99

        for (locale in allLocales) {
            val lang = locale.getDisplayLanguage(Locale.ENGLISH)
            val country = locale.getDisplayCountry(Locale.ENGLISH)
            print(locale.toString().padEnd(30))
            print('\t')
            print("$lang, $country".padEnd(50))
            print('\t')
            print(NumberFormat.getInstance(locale).format(value).padEnd(30))
            print('\t')
            print(NumberFormat.getCurrencyInstance(locale).format(value).padEnd(30))
            print('\n')
        }
    }
}
