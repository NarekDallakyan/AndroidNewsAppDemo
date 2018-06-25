package am.ith.app.web_serviece.model


data class AppResponse(
        val success: Boolean,
        val errors: List<Any>,
        val internal_errors: List<Any>,
        val metadata: List<Metadata>
) {

    data class Metadata(
            val category: String,
            val title: String,
            val body: String,
            val shareUrl: String,
            val coverPhotoUrl: String,
            val date: Int,
            val gallery: List<Gallery>,
            val video: List<Video>
    ) {

        data class Video(
                val title: String,
                val thumbnailUrl: String,
                val youtubeId: String
        )


        data class Gallery(
                val title: String,
                val thumbnailUrl: String,
                val contentUrl: String
        )
    }
}