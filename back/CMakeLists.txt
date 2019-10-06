cmake_minimum_required(VERSION 3.14)
project(cryptography)

set(CMAKE_CXX_STANDARD 17)

add_executable(cryptography main.cpp crypt_utils.cpp diffie_hellman_user.cpp diffie_hellman_channel.cpp diffie_hellman_channel.hpp key_generator.cpp key_generator.hpp diffie_hellman_generator.cpp diffie_hellman_generator.hpp)