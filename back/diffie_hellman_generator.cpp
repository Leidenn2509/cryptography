//
// Created by Alexander Raish on 30/09/2019.
//

#include <random>
#include "diffie_hellman_generator.hpp"
#include "crypt_utils.hpp"

diffie_hellman_generator::diffie_hellman_generator(uint64_t p, uint64_t g)
    : seed(), random(p / 2, p), m_p(p), m_g(g) {}

uint64_t diffie_hellman_generator::generate_private_key(uint64_t p) {
    return random(seed);
}

uint64_t diffie_hellman_generator::generate_public_key(uint64_t private_key) {
    return fast_pow(m_g, private_key, m_p);
}


