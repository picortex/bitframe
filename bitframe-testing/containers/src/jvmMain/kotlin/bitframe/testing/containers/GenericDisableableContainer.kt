package bitframe.testing.containers

import org.testcontainers.containers.GenericContainer
import org.testcontainers.images.builder.ImageFromDockerfile

class GenericDisableableContainer<T : GenericDisableableContainer<T>?>(
    imageFromDockerfile: ImageFromDockerfile
) : GenericContainer<T>(imageFromDockerfile) {
    private var isActive = false

    override fun start() {
        if (isActive) {
            super.start()
        }
    }

    fun isActive(isActive: Boolean): GenericDisableableContainer<*> {
        this.isActive = isActive
        return this
    }
}