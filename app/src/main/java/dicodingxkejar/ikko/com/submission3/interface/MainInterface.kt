package dicodingxkejar.ikko.com.submission3.`interface`

import dicodingxkejar.ikko.com.submission3.model.Match

interface MainInterface {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<Match>?)
}