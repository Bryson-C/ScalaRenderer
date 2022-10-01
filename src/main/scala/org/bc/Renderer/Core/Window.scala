package org.bc.Renderer.Core

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL

class Window(var width: Int, var height: Int, var title: String, vsync: Boolean):
    private var windowObject: Long = 0
    def this(_width: Int, _height: Int, _title: String) = {
        this(_width, _height, _title, true)

        val glfwResult = glfwInit()
        if !glfwResult then
            throw RuntimeException("Failed Initializing GLFW")


        windowObject = glfwCreateWindow(width, height, title, 0, 0)

        if windowObject == 0 then
            throw RuntimeException("Failed Creating Window Object")

        glfwMakeContextCurrent(windowObject)
        // for vsync
        if vsync then glfwSwapInterval(1)

        GL.createCapabilities()
    }
    def getWindowObject: Long = windowObject
    def shouldClose: Boolean = glfwWindowShouldClose(windowObject)
    def swapBuffers(): Unit = glfwSwapBuffers(windowObject)
