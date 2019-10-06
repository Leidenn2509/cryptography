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

bool diffie_hellman_user::generate_session_private_key_with(const std::shared_ptr<diffie_hellman_user> &other) {
    connections.insert_or_assign(other, fast_pow(other->get_public_key(), m_private_key, m_p));
    std::cout << connections.at(other) << std::endl;
    return true;
}

bool diffie_hellman_user::send(std::string message, const std::shared_ptr<diffie_hellman_user> to) {
//    uint64_t key;
//    auto buf = connections.find(to);
//    if (buf != connections.end()) {
//        key = buf->second;
//    } else {
//        generate_session_private_key_with(to);
//        key = connections.at(to);
//    }
//    for(unsigned char c : message) {
//        std::cout <<
//    }
    return false;
}
