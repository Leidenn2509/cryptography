cmake_minimum_required(VERSION 3.14)
project(cryptography)

set(CMAKE_CXX_STANDARD 17)

add_executable(cryptography main.cpp crypt_utils.cpp)