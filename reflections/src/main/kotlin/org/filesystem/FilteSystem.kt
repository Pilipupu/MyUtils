package org.filesystem

import org.error.OperationFailureError
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.PosixFilePermissions

object OSMock {
    var fileSystem: FileSystem? = null
    val isNativeFileSystem = fileSystem == null
}

object OS {
    private val fs = FileSystems.getDefault()

    fun filesystem(): FileSystem {
        return if (OSMock.fileSystem != null) {
            OSMock.fileSystem!!
        } else {
            fs
        }
    }

    fun writeText(path: String, content: String, perm: String? = null) {
        val p = openFile(path, perm)
        Files.write(p, content.toByteArray())
    }

    fun readText(path: String): String {
        return readLines(path).joinToString("\n")
    }

    fun readLines(path: String): List<String> {
        if (!isRegularFile(path)) {
            throw OperationFailureError("$path is not a file")
        }

        return Files.readAllLines(openFile(path))
    }

    fun isRegularFile(path: String): Boolean {
        val p = filesystem().getPath(path)
        return Files.isRegularFile(p)
    }

    private fun openFile(path: String, perm:String? = null): Path {
        val p = filesystem().getPath(path)
        val dir = p.parent
        if (!Files.exists(dir)) {
            Files.createDirectories(dir)
        }

        if (!Files.isRegularFile(p)) {
            if (perm != null) {
                Files.createFile(p, PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(perm)))
            } else {
                Files.createFile(p)
            }
        }

        return p
    }
}

