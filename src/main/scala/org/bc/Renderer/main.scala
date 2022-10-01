

import org.lwjgl.glfw.GLFW._
import org.lwjgl.opengl._
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL15._
import org.lwjgl.opengl.GL20._
import org.lwjgl.opengl.GL30._

import scala.collection.mutable.ListBuffer
import scala.io.Source._

import org.bc.Renderer.Core.Window
import org.bc.Renderer.Shaders.{Shader,ShaderProgram}




@main def main(): Unit = {
    val windowObject = Window(800, 600, "Window")


    glClearColor(0.15f, 0.15f, 0.15f, 1.0f)

    val vertexShader = Shader("D:\\ScalaRenderer\\src\\main\\scala\\org\\bc\\Renderer\\Shaders\\vertex.glsl", GL_VERTEX_SHADER)
    val fragmentShader = Shader("D:\\ScalaRenderer\\src\\main\\scala\\org\\bc\\Renderer\\Shaders\\fragment.glsl", GL_FRAGMENT_SHADER)

    val shaderProgram = ShaderProgram(Array(vertexShader, fragmentShader))
    shaderProgram.link()


    val vertices = ListBuffer[Float](
        -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.0f,  0.5f, 0.0f
    )


    // These Little Fuckers Cant Be 0
    val vertexBufferObject = 1;
    glGenBuffers(Array(vertexBufferObject))

    val vertexArrayObject = 2;
    glGenVertexArrays(Array(vertexArrayObject))



    while !windowObject.shouldClose do
        glfwPollEvents()


        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)


        glBindVertexArray(vertexArrayObject)
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject)
        glBufferData(GL_ARRAY_BUFFER, vertices.toArray, GL_STATIC_DRAW)

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0)
        glEnableVertexAttribArray(0)


        shaderProgram.use()
        glBindVertexArray(vertexArrayObject)
        try {
            glDrawArrays(GL_TRIANGLES, 0, 3)
        } catch {
            case x: Error => println(s"Error: $x")
        }

        windowObject.swapBuffers()

}