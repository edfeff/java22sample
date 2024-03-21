public class Jep464_E1 {
    public static void main(String[] args) {
        var application = new MyHandler();
        var framework = new Framework(application);
        framework.service(new Request(), new Response());
    }

    static class FrameworkContext {
        private long startTime;
        private long spentTime;
    }

    interface Handler {
        void handle(Request request, Response response);
    }

    static class MyHandler implements Handler {
        @Override
        public void handle(Request request, Response response) {
            //这里是无法读取CONTEXT的，限制了用户代码的访问
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    record Request() {
    }

    record Response() {
    }

    static class Framework {
        private final Handler handler;
        private final static ScopedValue<FrameworkContext> CONTEXT
                = ScopedValue.newInstance();   // (1)

        Framework(Handler handler) {
            this.handler = handler;
        }

        void service(Request request, Response response) {
            var context = createContext(request);
            ScopedValue.where(CONTEXT, context)            // (2)
                    .run(() -> { //CONTEXT 在此处后可以访问
                        preRequest();
                        handler.handle(request, response);
                        postRequest();
                        //CONTEXT 在此后不可以访问，限定了context的访问访问
                    });
        }

        private void preRequest() {
            var context = CONTEXT.get();
            context.startTime = System.currentTimeMillis();
        }

        private void postRequest() {
            var context = CONTEXT.get();
            context.spentTime = System.currentTimeMillis() - context.startTime;
            System.out.println("request spent " + context.spentTime + " ms");
        }

        FrameworkContext createContext(Request request) {
            return new FrameworkContext();
        }
    }
}
