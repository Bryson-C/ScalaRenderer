package org.bc.Renderer.Shaders

import org.lwjgl.opengl.GL20._

object ShaderUtil {
    def readShader(path: String): String = {
        val source = scala.io.Source.fromFile(path);
        val lines = for i <- source.getLines() yield i
        val shaderSource = lines.mkString("\n")
        source.close()
        shaderSource
    }
}

class Shader():
    private var path = String()
    private var source = String()
    private var shaderType = 0
    private var shaderObject = 0

    def this(_path: String, _shaderType: Int) = {
        this()
        val shaderSource = ShaderUtil.readShader(_path)
        path = _path
        source = shaderSource
        shaderType = _shaderType

        shaderObject = glCreateShader(shaderType)
        glShaderSource(shaderObject, shaderSource)
        glCompileShader(shaderObject)
        val shaderCompilationResult = glGetShaderInfoLog(shaderObject);
        if shaderCompilationResult.nonEmpty then
            println(s"VertexShaderResult: ${shaderCompilationResult}");
    }
    def getShader: Int = shaderObject

class ShaderProgram():
    private var shaderProgram = 0
    def this(shaders: Array[Shader]) = {
        this()
        shaderProgram = glCreateProgram()

        for shader <- shaders do
            glAttachShader(shaderProgram, shader.getShader)
    }
    def link(): Unit = glLinkProgram(shaderProgram)
    def use(): Unit = glUseProgram(shaderProgram)
