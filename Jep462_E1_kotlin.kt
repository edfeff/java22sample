import kotlinx.coroutines.*

fun main() = runBlocking {
    val mainTask: Job
    var subTask1: Job? = null
    var subTask2: Job? = null
    //父协程
    mainTask = launch {
        //3个子协程
        subTask1 = launch {
            //1秒
            println("subTask1 start")
            delay(1000L)
            println("subTask1 finish")
        }

        subTask2 = launch {
            //3秒
            println("subTask2 start")
            delay(3000L)
            println("subTask2 finish")
        }
    }
    delay(500L)
    //遍历父协程的子协程集
    mainTask.children.forEachIndexed { index, task ->
        when (index) {
            0 -> println("subTask1 === task is ${subTask1 === task}")
            1 -> println("subTask2 === task is ${subTask2 === task}")
        }
    }
    //等待父协程执行完成
    mainTask.join()
    println("finish")
}
