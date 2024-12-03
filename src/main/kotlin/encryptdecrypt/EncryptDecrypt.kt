package encryptdecrypt

import encryptdecrypt.AESHelper.decrypt
import helpers.printf
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

//https://viblo.asia/p/ma-hoa-du-lieu-voi-thuat-toan-aes-advanced-encryption-standard-trong-android-yMnKMLGz57P
object AESHelper {
    private const val ALGORITHM = "AES"
    private const val HEX = "0123456789ABCDEF"
    const val EMAIL_KEY = "EMAIL_KEY"
    const val PASSWORD_KEY = "PASSWORD_KEY"

    // sử dụng hàm này khi cần mã hóa dữ liệu
    @Throws(Exception::class)
    fun encrypt(seed: String, clearText: String): String {
        val rawKey = getRawKey(seed.toByteArray())
        val result = encrypt(rawKey, clearText.toByteArray())
        return toHex(result)
    }

    // sử dụng hàm này khi cần giải mã dữ liệu
    @Throws(Exception::class)
    fun decrypt(seed: String, encrypted: String): String {
        val rawKey = getRawKey(seed.toByteArray())
        val enc = toByte(encrypted)
        val result = decrypt(rawKey, enc)
        return String(result)
    }

    @Throws(Exception::class)
    private fun getRawKey(seed: ByteArray): ByteArray {
        val kgen = KeyGenerator.getInstance(ALGORITHM) // tạo ra các khóa bí mật có thể dùng lại
        val sr: SecureRandom = SecureRandom.getInstance("SHA1PRNG") // tạo ra mã ngẫu nhiên
        sr.setSeed(seed) //Reseeds this random object, using the eight bytes contained in the given long seed.
        kgen.init(128, sr) // sử dụng AES-128
        val skey = kgen.generateKey()
        return skey.encoded // return the raw key bytes as the result of a getEncoded method call.
    }

    @Throws(Exception::class)
    private fun encrypt(raw: ByteArray, clear: ByteArray): ByteArray {
        val skeySpec = SecretKeySpec(raw, ALGORITHM)
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec)
        val encrypted = cipher.doFinal(clear)
        return encrypted
    }

    @Throws(Exception::class)
    private fun decrypt(raw: ByteArray, encrypted: ByteArray): ByteArray {
        val skeySpec = SecretKeySpec(raw, ALGORITHM)
        val cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, skeySpec)
        val decrypted = cipher.doFinal(encrypted)
        return decrypted
    }

    /*fun toHex(txt: String): String {
        return toHex(txt.toByteArray())
    }

    fun fromHex(hex: String): String {
        return String(toByte(hex))
    }*/

    // chuyển đổi String thành mảng byte
    @OptIn(ExperimentalStdlibApi::class)
    private fun toByte(hexString: String): ByteArray {
        val len = hexString.length / 2
        val result = ByteArray(len)
        for (i in 0..<len) {
            result[i] = hexString.substring(2 * i, 2 * i + 2).toInt(16).toByte()
        }
        return result
    }

    // chuyển đổi một mảng byte trở thành String
    private fun toHex(buf: ByteArray?): String {
        if (buf == null) return ""
        val result = StringBuffer(2 * buf.size)
        //for (i in buf.indices) {
        buf.indices.forEach { i ->
            appendHex(result, buf[i])
        }
        return result.toString()
    }

    // dịch chuyển bit vào thêm mã hex
    private fun appendHex(sb: StringBuffer, b: Byte) {
        sb.append(HEX[(b.toInt() shr 4) and 0x0f]).append(HEX[b.toInt() and 0x0f])
    }
}


fun main() {
    // mã hóa
    val encryptEmail = AESHelper.encrypt(AESHelper.EMAIL_KEY, "email").also {
        printf("Encrypted email = $it")
    }
    val encryptedPassword = AESHelper.encrypt(AESHelper.PASSWORD_KEY, "password").also {
        printf("Encrypted password = $it")
    }

    // giải mã
    decrypt(AESHelper.EMAIL_KEY, encryptEmail).also {
        printf("Email = $it")
    }
    decrypt(AESHelper.PASSWORD_KEY, encryptedPassword).also {
        printf("Password = $it")
    }
}
