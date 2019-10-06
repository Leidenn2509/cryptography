//
// Created by Alexander Raish on 26/09/2019.
//

#include <iostream>
#include "diffie_hellman_user.hpp"
#include "crypt_utils.hpp"

diffie_hellman_user::diffie_hellman_user(uint64_t p, uint64_t g, std::random_device seed) : m_p(p), m_g(g) {
    m_private_key = std::uniform_int_distribution<uint64_t>(m_p / 2, m_p)(seed);
    m_public_key = fast_pow(m_g, m_private_key, m_p);
    std::cout << m_private_key << std::endl << m_public_key << std::endl;
}

uint64_t diffie_hellman_user::get_public_key() const {
    return m_public_key;
}
