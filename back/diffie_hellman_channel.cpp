//
// Created by Alexander Raish on 30/09/2019.
//

#include "diffie_hellman_channel.hpp"

diffie_hellman_channel::diffie_hellman_channel(std::shared_ptr<diffie_hellman_user> alice,
                                               std::shared_ptr<diffie_hellman_user> bob) : m_a(alice), m_b(bob) {
    // TODO throw exc if false
    m_a->generate_session_private_key_with(m_b);
    m_b->generate_session_private_key_with(m_a);
}
