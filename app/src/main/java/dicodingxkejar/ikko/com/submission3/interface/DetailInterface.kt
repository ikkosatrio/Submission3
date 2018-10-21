package dicodingxkejar.ikko.com.submission3.`interface`

import dicodingxkejar.ikko.com.submission3.model.Team

interface DetailInterface {
    fun showLoading()
    fun hideLoading()
    fun showListTeam(data: List<Team>?, data2: List<Team>?)
}