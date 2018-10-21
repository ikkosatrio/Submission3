package dicodingxkejar.ikko.com.submission3.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson
import dicodingxkejar.ikko.com.submission3.DetailActivity

import dicodingxkejar.ikko.com.submission3.R
import dicodingxkejar.ikko.com.submission3.`interface`.MainInterface
import dicodingxkejar.ikko.com.submission3.adapter.MatchAdapter
import dicodingxkejar.ikko.com.submission3.api.API
import dicodingxkejar.ikko.com.submission3.model.Favorite
import dicodingxkejar.ikko.com.submission3.model.Match
import dicodingxkejar.ikko.com.submission3.presenter.MainPresenter
import dicodingxkejar.ikko.com.submission3.presenter.NextPresenter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PastFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PastFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PastFragment : Fragment(), AnkoComponent<Context>, MainInterface {
    // TODO: Rename and change types of parameters


    private lateinit var matchadapter: MatchAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var listMatch: RecyclerView


    private lateinit var past: MainPresenter

    private var favorites: MutableList<Favorite> = mutableListOf()
    private var events: MutableList<Match> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = API()
        val gson = Gson()
        past = MainPresenter(this, api, gson)

        matchadapter = MatchAdapter(events) {
            startActivity<DetailActivity>(
                    "TeamHomeId" to it.idHomeTeam,
                    "TeamAwayId" to it.idAwayTeam,
                    "EventId" to it.idEvent)
        }
        listMatch.adapter = matchadapter

        past.getList(API.LEAGUE_ID)

        swipeRefresh.onRefresh {
            past.getList(API.LEAGUE_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View =  with(ui){
        linearLayout{
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL


            swipeRefresh = swipeRefreshLayout{
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout{
                    lparams(width = matchParent, height = wrapContent)

                    listMatch = recyclerView{
                        id = R.id.listMatchRV
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {

                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
    }

    private fun View.visible(){
        visibility = View.VISIBLE
    }

    private fun View.invisible(){
        visibility = View.INVISIBLE
    }

    override fun showLoading() {
        print("HORE1")
        progressBar.visible()
    }

    override fun hideLoading() {
        print("HORE2")
        progressBar.invisible()
    }

    override fun showList(data: List<Match>?) {
        print("HORE")

        favorites.clear()
        swipeRefresh.isRefreshing = false
        events.clear()

        data?.let {
            Log.d("HORE",data.toString())
            events.addAll(data)
            matchadapter.notifyDataSetChanged()
        } ?: toast(getString(R.string.STR_NO_DATA))
    }

}
