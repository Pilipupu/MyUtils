package org.os

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.filesystem.OS
import org.filesystem.OSMock
import org.junit.jupiter.api.Test

class FileSystemTest {
    @Test
    fun testFileSystem() {
        OSMock.fileSystem = Jimfs.newFileSystem(Configuration.unix())

        OS.writeText("/path/cobbler_ks_mirror/iso1/test", "1234567")

        println(OS.readText("/path/cobbler_ks_mirror/iso1/test"))
    }
}