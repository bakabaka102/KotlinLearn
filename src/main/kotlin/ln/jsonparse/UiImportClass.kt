/**
 * This is an auto-generated file!
 * Please DON'T edit it manually!
 */

package ln.jsonparse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.Serializable


@Serializable
data class UiJson(
    val drawerMenu: List<MenuItem> = listOf(),
    val setup: SetupMenu = SetupMenu(),
)

@Serializable
data class MenuItem(
    val type: DrawMenuType = DrawMenuType.Item,
    val name: String? = "",
    val menuId: DrawMenuId = DrawMenuId.None,
    val icon: String? = null,
    val action: String? = null,
    val menuItems: List<MenuItem> = listOf()
)

@Serializable(with = DrawMenuTypeSerializer::class)
enum class DrawMenuType {
    Item,
    PasswordProtectedMenuNode,
    PasswordProtectedMenuItem,
    Title;

    companion object {
        fun fromString(value: String): DrawMenuType? {
            return values().find { it.name == value }
        }
    }
}

object DrawMenuTypeSerializer : KSerializer<DrawMenuType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("DrawMenuType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: DrawMenuType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): DrawMenuType {
        val name = decoder.decodeString()
        return DrawMenuType.fromString(name) ?: throw IllegalArgumentException("Deserialize Unknown DrawMenuType: $name")
    }
}

@Serializable(with = DrawMenuIdSerializer::class)
enum class DrawMenuId {
    Reports, ReprintReceipt, Logon, Settlement,
    BatchNode,
    BatchHistory,
    BatchChangeNbr,
    BatchDelete,
    BatchDeleteReversal,
    BatchInquiry,
    GiftBatchInquiry,

    ClerkNode,
    ClerkAdd,
    ClerkDelete,
    ClerkPrint,
    ClerkAutoAdd,
    ClerkPrompt,
    ClerkWording,
    ClerkDisplaySettings,

    SetupNode,
    SetupMerchant,
    SetupCommunication,
    SetupReceiptsAndReports,
    SetupTransactions,
    SetupTerminal,
    SetupDemo,
    SetupSemiIntegrated,
    SetupDownloadOnly,

    HostManageNode,
    EmvPublicKeyDownload,
    KeyDownload,

    SecuritySettingNode,
    PwParams,
    AddUser,
    ChangePassword,
    DeleteUser,

    MaintenanceNode,
    TerminalDownloadParameters,
    CommunicationTest,
    KeyRequest,
    Initialization,

    HcpAdminNode,
    HcpPycDownloads,
    HcpSettlement,
    HcpConnectivity,


    ShiftChange,
    Encryption,
    BatchInquiryReport,
    TerminalInformation,

    LanguageChange,

    InstallerMenu,

    None;

    companion object {
        fun fromString(value: String): DrawMenuId? {
            return values().find { it.name == value }
        }
    }
}

object DrawMenuIdSerializer : KSerializer<DrawMenuId> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("DrawMenuId", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: DrawMenuId) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): DrawMenuId {
        val name = decoder.decodeString()
        return DrawMenuId.fromString(name) ?: throw IllegalArgumentException("Deserialize Unknown DrawMenuId: $name")
    }
}

@Serializable
data class SetupMenu(
    val credit: List<SetupItem> = listOf(),
    val debit: List<SetupItem> = listOf(),
    val tip: List<SetupItem> = listOf(),
    val prompt: List<SetupItem> = listOf(),
    val merchant: List<SetupItem> = listOf(),
    val header: List<SetupItem> = listOf(),
    val report: List<SetupItem> = listOf(),
    val demo: List<SetupItem> = listOf(),
    val downloadOnly: List<SetupItem> = listOf()
)

@Serializable
data class SetupItem(
    val type: SetupItemType = SetupItemType.InputNumeric,
    val title: String = "",
    val dropDownValues: List<String> = mutableListOf(),
    val default: String? = null,
    val action: String? = null
)

@Serializable(with = SettingItemTypeSerializer::class)
enum class SetupItemType {
    InputNumeric,
    InputText,
    DropDown,
    Switch;

    companion object {
        fun fromString(value: String): SetupItemType? {
            return SetupItemType.values().find { it.name == value }
        }
    }
}

object SettingItemTypeSerializer : KSerializer<SetupItemType> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("SettingItemType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: SetupItemType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): SetupItemType {
        val name = decoder.decodeString()
        return SetupItemType.fromString(name) ?: throw IllegalArgumentException("Deserialize Unknown DrawMenuId: $name")
    }
}