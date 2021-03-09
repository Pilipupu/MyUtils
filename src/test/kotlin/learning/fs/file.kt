package learning.fs

import json.CobblerEnvStruct
import json.jsonEncode
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class file {
    /**
     * Path相对路径默认为项目根路径
     */
    @Test
    fun testCreateFile() {
        Files.createFile(Paths.get("demo"))
    }

    @Test
    fun testCreateTempFile() {
        println(Files.createTempFile("demo", "a.txt").toAbsolutePath())
    }

    /**
     * 会覆盖原来的内容，不是追加
     */
    @Test
    fun testWriteText() {
        Files.write(Paths.get("demo"), "hello".toByteArray())
    }

    /**
     * 如果最开始没有文件会自动创建，但是不会创建目录
     */
    @Test
    fun testWriteTextNoFile() {
        Files.write(Paths.get("demo1"), "hello".toByteArray())
    }

    /**
     * 会自动创建文件
     */
    @Test
    fun testWriteFile() {
        File("demo").writeText("demo")
        File("demo3").writeText("demo")
    }

    @Test
    fun testFileParent() {
        val p = Files.createDirectory(Path.of("tmpDir"))
        val f = p.resolve("demo")
        val fp = Files.createFile(f)
        println(fp.toAbsolutePath().parent)
        println(fp.parent)
    }

    /**
     * 创建文件，parent为/前面的路径
     */
    @Test
    fun testFileParent2() {
        val fp = Files.createFile(Path.of("tmpDir/demo3"))
        println(fp.parent)

        val p = Files.createFile(Path.of("./demo6"))
        println(p.parent)
    }

    @Test
    fun testFileParent3() {
        println(Files.isWritable(Path.of("tmpDir")))
        Files.write(Path.of("tmpDir"),"hello".toByteArray())
    }

    /**
     * File有Path是String类型的
     * File的parent就是上级目录（绝对路径下有,相对路径下为null）
     *
     * Path行为跟File一样
     *
     * fs.resolve(File f)能得到绝对路径
     */
    @Test
    fun testFileParent4() {
        val f = File("/tmp/a")
        println(f.parent)

        val f1 = File("demo")
        println(f1.parent)

        println(Path.of(f1.absolutePath).parent)

        val p = Path.of("/tmp/a")
        println(p.parent)
    }

    /**
     * 使用Files.createDirectory()创建目录时，如果存在同名文件不能创建成功
     * Files.createDirectorys() 如果存在不会报错
     *
     * 使用File.mkdir()创建
     */
    @Test
    fun createDirWhenHaveSameNameFile() {
        File("demo").mkdir()
        Files.createDirectories(Path.of("demo"))
        Files.createDirectory(Path.of("demo"))

        Files.createFile(Path.of("demo"))
    }

    @Test
    fun testWriteOver() {
        val expr = CobblerEnvStruct().apply {
            name = "compute-bond"
            tag = "default"
            path = "/var/lib/cobbler/kickstarts/compute-bond.ks"
            description = "compute env for zstack-node"
            mode = "compute"
            iso = "compute env for zstack-node"
            state = "Initialize"
            default = true
        }

        Files.writeString(Path.of("demo"), expr.jsonEncode(), StandardOpenOption.SYNC, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    }
}