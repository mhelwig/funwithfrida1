#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_net_codemetrix_funwithfrida_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello, World (native method)";
    return env->NewStringUTF(hello.c_str());
}

