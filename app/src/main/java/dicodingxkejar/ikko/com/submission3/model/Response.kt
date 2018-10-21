package dicodingxkejar.ikko.com.submission3.model

import com.google.gson.annotations.SerializedName

data class Response(
        @field:SerializedName("events")
        val events: List<Match>? = null,

        @field:SerializedName("teams")
        val teamId: List<Team>? = null
)