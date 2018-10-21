package dicodingxkejar.ikko.com.submission3.adapter

import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import dicodingxkejar.ikko.com.submission3.R
import dicodingxkejar.ikko.com.submission3.R.id.*
import dicodingxkejar.ikko.com.submission3.model.Match
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk25.listeners.onClick

class MatchAdapter (private val events: List<Match>, private val listener: (Match) -> Unit)
    : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>(){

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItems(events[position], listener)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = events.size

    class MatchUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                cardView{
                    id = R.id.itemCardView
                    lparams(width = matchParent, height = wrapContent){
                        topMargin = dip(16)
                        rightMargin = dip(16)
                        leftMargin = dip(16)
                    }

                    linearLayout {
                        orientation = LinearLayout.VERTICAL
                        padding = dip(16)

                        textView {
                            id = R.id.dateMatchTV
                            textColor = Color.parseColor("#F9AC30")
                            gravity = Gravity.CENTER
                        }.lparams {
                            width = matchParent
                        }

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL

                            textView {
                                id = R.id.nameHomeTV
                                gravity = Gravity.CENTER
                            }.lparams {
                                width = matchParent
                                weight = 1f
                            }

                            textView {
                                id = R.id.scoreTV
                                gravity = Gravity.CENTER
                            }.lparams {
                                width = matchParent
                                weight = 1f
                            }

                            textView {
                                id = R.id.nameAwayTV
                                gravity = Gravity.CENTER
                            }.lparams {
                                width = matchParent
                                weight = 1f
                            }
                        }
                    }
                }
            }
        }
    }

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val homeName: TextView = view.find(nameHomeTV)
        private val awayName: TextView = view.find(nameAwayTV)
        private val score: TextView = view.find(scoreTV)
        private val matchDate: TextView = view.find(dateMatchTV)
        val cv: CardView = view.find(itemCardView)

        fun bindItems(events: Match, listener: (Match) -> Unit) {
            homeName.text = events.strHomeTeam
            awayName.text = events.strAwayTeam
            if (events.intHomeScore != null) {
                score.text = events.intHomeScore + " VS " + events.intAwayScore
            } else {
                score.text = "? VS ?"
            }
            matchDate.text = events.dateEvent
            cv.onClick {
                listener(events)
            }
        }
    }
}


