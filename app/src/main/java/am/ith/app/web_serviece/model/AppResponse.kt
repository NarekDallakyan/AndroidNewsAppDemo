package am.ith.app.web_serviece.model


data class AppResponse(
        val success: Boolean, // true
        val errors: List<Any>,
        val internal_errors: List<Any>,
        val metadata: List<Metadata>
) {

    data class Metadata(
            val category: String, // Entertainment
            val title: String, // Սելենա Գոմեսի թոփլես ֆոտոշարքն ու զրույցը սիրային հարցերի շուրջ
            val body: String, // Սելենա Գոմեսը դարձել է &ldquo;V Magazine&rdquo;-ի նոր համարի հերոսուհին: 22-ամյա երգչուհին ամսագրի համար լուսանկարվել է թոփլես` առանց կրծկալի, նաեւ հարցազրույց է տվել` անկեղծանալով սիրային հարցերի շուրջ եւ ներկայացնելով ապագայի պլանները:<br /><br /><strong>Կյանքի դասերի մասին</strong><br /><br />Հաջորդ անգամ, երբ ընկեր ունենամ, ամեն ինչ այլ կերպ կլինի&hellip; Ամեն դեպքում, դա շուտ չի լինի&hellip;<br /><br /><strong>Ջասթին Բիբերի հետ հարաբերությունների մասին</strong><br /><br />Այն, ինչ ունեցել ենք, հենց մենք ենք: Ինչ-որ առումով աշխարհը դեմ էր: Տարօրինակ էր, բայց իր ձեւի մեջ հիանալի: Բայց չէի ցանկանա ամեն ինչ կրկնել&hellip;<br /><br /><strong>Դեպրեսիայի մասին</strong><br /><br />Մի քանի ամիս տնից դուրս չէի գալիս&hellip; Կարծում եմ` մի քիչ խենթացել եմ:<br /><br /><strong>BRAVO.am<br />Աղբյուրը` &ldquo;V Magazine&rdquo;</strong>
            val shareUrl: String, // http://bravo.am/news/Սելենա-Գոմեսի-թոփլես-ֆոտոշարքն-ու-զրույցը-սիրային-հարցերի-շուրջ/
            val coverPhotoUrl: String, // http://bravo.am/uploads/2015/02/top_6bf63446a6aedadd55fb02f76c807818.jpg
            val date: Int, // 1424411820
            val gallery: List<Gallery>,
            val video: List<Video>
    ) {

        data class Video(
                val title: String, // Թամար Կապրելյանը
                val thumbnailUrl: String, // http://bravo.am/uploads/2015/02/1424430592_2419799.jpg
                val youtubeId: String // PusyhO-xANg
        )


        data class Gallery(
                val title: String, // Սելենա Գոմեսը
                val thumbnailUrl: String, // http://bravo.am/uploads/2015/02/1424411820_9059957.jpg
                val contentUrl: String // http://bravo.am/uploads/2015/02/big_1424411820_9059957.jpg
        )
    }
}