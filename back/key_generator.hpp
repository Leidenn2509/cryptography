//
// Created by Alexander Raish on 30/09/2019.
//

#ifndef CRYPTOGRAPHY_KEY_GENERATOR_HPP
#define CRYPTOGRAPHY_KEY_GENERATOR_HPP


#include <cstdint>

class key_generator {
public:
    virtual uint64_t generate_public_key(uint64_t private_key) = 0;
    virtual uint64_t generate_private_key(uint64_t p) = 0;
};


#endif //CRYPTOGRAPHY_KEY_GENERATOR_HPP
