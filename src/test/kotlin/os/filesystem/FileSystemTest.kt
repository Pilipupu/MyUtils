package os.filesystem

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.apache.commons.io.FileUtils
import org.filesystem.OS
import org.filesystem.OSMock
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * 关联learning/fs
 */
class FileSystemTest {
    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAll() {
            OSMock.fileSystem = Jimfs.newFileSystem(Configuration.unix())
        }
    }

    @Test
    fun testFileSystem() {
        OS.writeText("/path/cobbler_ks_mirror/iso1/test", "1234567")

        println(OS.readText("/path/cobbler_ks_mirror/iso1/test"))
    }

    /**
     * copy 内存文件系统文件到 本地
     */
    @Test
    fun testCopyJimFsFileToNativeFS() {
        OS.writeText("/path/cobbler_ks_mirror/iso1/test", "1234567")
        Files.copy(OS.getPath("/path/cobbler_ks_mirror/iso1/test"), Paths.get("demo2"))
    }

    /**
     * Files.copy需要目标文件的父目录存在，不会自动创建父目录
     * File()也需要父目录的文件存在
     */
    @Test
    fun testCopyJimFsFile() {
        val src = OS.filesystem().getPath(System.getProperty("java.io.tmpdir")).resolve("zops")
//        Files.createDirectories(src.parent)
//        Files.createFile(src)
        OS.writeText(src.toString(), "demo")
        val dst = "/etc/cobbler/dhcp.template"
//        OS.getPath(dst)
//        OS.writeText(dst, "demo")
        println(OS.getPath(dst).toString())
        Files.createDirectories(OS.getPath("/etc/cobbler"))
        Files.copy(OS.getPath(src.toString()), OS.getPath(dst))
//        File("demo").copyTo(File("${System.getProperty("user.home")}/test/demo4"))
    }

    /**
     * Files里面有FileSystemProvider可以对不同的fs进行操作
     * FileUtils不行
     */
    @Test
    fun testCopyNativeFsToJimFs() {
        OS.writeText("/path/cobbler_ks_mirror/iso1/test", "1234567")
//        Files.copy(Paths.get("demo2"),OS.getPath("/path/cobbler_ks_mirror/iso1/test"))
        Files.copy(OS.getPath("/path/cobbler_ks_mirror/iso1/test"),Paths.get("demo3"))
    }

    /**
     * Path有getFileSystem()方法，说明Path关联fs
     * Path.toFile可以转换成File，但是需要是
     * default fs
     * Path跟URI可以互相转换,URI是网络上的资源定位，所以不限制于
     * 单个fs
     *
     * */
    @Test
    fun testFileUtils() {
        FileUtils.copyFile(OS.getPath("/path/cobbler_ks_mirror/iso1/test").toFile(),Paths.get("demo").toFile())
    }

    /**
     * 不是file开头的uri不能专成file
     */
    @Test
    fun testFileUtilsCopyToWithURI() {
        OS.writeText("/path/cobbler_ks_mirror/iso1/test", "1234567")
        println(OS.filesystem().getPath("/path/cobbler_ks_mirror/iso1/test").toUri())
        println(Paths.get("demo").toUri())
        val src = File(OS.getPath("/path/cobbler_ks_mirror/iso1/test").toUri())
        val desc = File(Paths.get("demo").toUri())
        FileUtils.copyFile(src,desc)
    }

    @Test
    fun testFileParent() {
        println(File("demo").parent)
        println(File("demo").exists())

        println(Paths.get("demo").parent)
    }

    /**
     * File构造函数只接受String或URI
     */
    @Test
    fun testCreateFileObjectWithJimFsPath() {
        val p = OS.getPath("demo")
        val uri = p.toUri()
        assertThrows<IllegalArgumentException> { File(uri) }
    }
}

/**
 * 拓展函数只能拓展非静态方法
 */
fun Path.copyForce() {

}