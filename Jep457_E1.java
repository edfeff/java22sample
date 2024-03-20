import java.io.IOException;
import java.lang.classfile.ClassFile;
import java.lang.constant.ClassDesc;
import java.lang.constant.MethodTypeDesc;

void main() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    final var className = "Hello";
    byte[] bytes = ClassFile.of().build(ClassDesc.of(className), classBuilder -> {
        classBuilder.withMethod("<init>", MethodTypeDesc.ofDescriptor("()V"), ClassFile.ACC_PUBLIC, methodBuilder -> {
            methodBuilder.withCode(codeBuilder -> {
                codeBuilder.aload(codeBuilder.receiverSlot());
                codeBuilder.invokespecial(ClassDesc.ofDescriptor("Ljava/lang/Object;"), "<init>", MethodTypeDesc.ofDescriptor("()V"));
                codeBuilder.getstatic(ClassDesc.ofDescriptor("Ljava/lang/System;"), "out", ClassDesc.ofDescriptor("Ljava/io/PrintStream;"));
                codeBuilder.ldc("hello world");
                codeBuilder.invokevirtual(ClassDesc.ofDescriptor("Ljava/io/PrintStream;"), "println", MethodTypeDesc.ofDescriptor("(Ljava/lang/String;)V"));
                codeBuilder.return_();
            });
        });
    });
    ClassLoader classLoader = new ClassLoader() {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if (name.equals(className)) {
                return defineClass(className, bytes, 0, bytes.length);
            }
            return super.findClass(name);
        }
    };
    Class<?> helloClass = classLoader.loadClass(className);
    Object o = helloClass.newInstance();
}