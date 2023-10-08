package designpattern.behavial_patterns.facade_pattern

interface IShare {

    fun setMessage(message: String)

    fun share()
}

class FaceBookShare : IShare {

    private var message = ""
    override fun setMessage(message: String) {
        this.message = message
    }

    override fun share() {
        println("Share $message on FaceBook")
    }
}

class TwitterShare : IShare {

    private var message = ""
    override fun setMessage(message: String) {
        this.message = message
    }

    override fun share() {
        println("Share $message on Twitter")
    }
}

class SocialMedialShare(
    private var faceBookShare: FaceBookShare? = null,
    private var twitterShare: TwitterShare? = null,
) {

    fun share(message: String) {
        faceBookShare?.setMessage(message)
        twitterShare?.setMessage(message)
        faceBookShare?.share()
        twitterShare?.share()
    }
}

fun main() {
    val socialMedialShare = SocialMedialShare(FaceBookShare())
    socialMedialShare.share("Hiiiii")
}