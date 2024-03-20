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
            println("subTask2 start")
            delay(3000L)
            println("subTask2 finish")
        }
    }
    delay(500L)
    //取消主任务
    mainTask.cancel()
    println("finish")
}
