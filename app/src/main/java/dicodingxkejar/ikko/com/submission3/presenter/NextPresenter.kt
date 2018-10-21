package dicodingxkejar.ikko.com.submission3.presenter

import com.google.gson.Gson
import dicodingxkejar.ikko.com.submission3.CoroutineContextProvider
import dicodingxkejar.ikko.com.submission3.`interface`.MainInterface
import dicodingxkejar.ikko.com.submission3.api.API
import dicodingxkejar.ikko.com.submission3.api.APIRequests
import dicodingxkejar.ikko.com.submission3.model.Response
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextPresenter(private val anInterface: MainInterface,
           private val api: API,
           private val gson: Gson,
           private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getList(match: String?){
        anInterface.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(api
                        .doRequest(APIRequests.getNextEvent(match)),
                        Response::class.java
                )
            }
            anInterface.showList(data.await().events)
            anInterface.hideLoading()
        }
    }

}