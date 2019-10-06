//
// Created by Alexander Raish on 30/09/2019.
//

#ifndef CRYPTOGRAPHY_DIFFIE_HELLMAN_GENERATOR_HPP
#define CRYPTOGRAPHY_DIFFIE_HELLMAN_GENERATOR_HPP


#include "key_generator.hpp"

class diffie_hellman_generator : public key_generator {
public:
    explicit diffie_hellman_generator(uint64_t p, uint64_t g);
    uint64_t generate_public_key(uint64_t private_key) override;
    uint64_t generate_private_key(uint64_t p) override;
private:
    std::random_device seed;
    std::uniform_int_distribution<uint64_t> random;
    uint64_t m_p;
    uint64_t m_g;
};


#endif //CRYPTOGRAPHY_DIFFIE_HELLMAN_GENERATOR_HPP
