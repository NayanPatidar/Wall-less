#include <jni.h>
#include "JNITesting_JNIExample.h"


JNIEXPORT jint JNICALL Java_com_example_jni_JNIExample_add(JNIEnv* env, jobject obj, jint a, jint b) {
	return a + b;
}