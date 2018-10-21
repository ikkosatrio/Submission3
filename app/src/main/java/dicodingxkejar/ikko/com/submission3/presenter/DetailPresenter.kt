package dicodingxkejar.ikko.com.submission3.presenter

import com.google.gson.Gson
import dicodingxkejar.ikko.com.submission3.`interface`.DetailInterface
import dicodingxkejar.ikko.com.submission3.api.API
import dicodingxkejar.ikko.com.submission3.api.APIRequests
import dicodingxkejar.ikko.com.submission3.model.Response
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPresenter(private val anInterface: DetailInterface,
                      private val api: API,
                      private val gson: Gson){

    fun getListBadge(teamHome: String?, teamAway: String?){
        anInterface.showLoading()

        async(UI){
            val data = bg {
                gson.fromJson(api
                        .doRequest(APIRequests.getLogoHome(teamHome)),
                        Response::class.java
                )
            }
            val data2 = bg {
                gson.fromJson(api
                        .doRequest(APIRequests.getLogoAway(teamAway)),
                        Response::class.java
                )
            }

            anInterface.showListTeam(data.await().teamId, data2.await().teamId)
            anInterface.hideLoading()
        }
    }
}