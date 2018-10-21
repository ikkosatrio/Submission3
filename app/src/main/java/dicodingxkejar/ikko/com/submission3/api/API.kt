package dicodingxkejar.ikko.com.submission3.api

import java.net.URL

class API{
    fun doRequest(url: String): String{
        return URL(url).readText()
    }

    companion object {
        const val BASE_URL = "https://www.thesportsdb.com/"
        const val API_KEY = "1"
        const val LEAGUE_ID = "4335"
    }
}