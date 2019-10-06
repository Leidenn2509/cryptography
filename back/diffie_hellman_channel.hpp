//
// Created by Alexander Raish on 30/09/2019.
//

#ifndef CRYPTOGRAPHY_DIFFIE_HELLMAN_CHANNEL_HPP
#define CRYPTOGRAPHY_DIFFIE_HELLMAN_CHANNEL_HPP


#include <memory>
#include "diffie_hellman_user.hpp"

class diffie_hellman_channel {
public:
    diffie_hellman_channel(std::shared_ptr<diffie_hellman_user> alice, std::shared_ptr<diffie_hellman_user> bob);

private:
    std::shared_ptr<diffie_hellman_user> m_a;
    std::shared_ptr<diffie_hellman_user> m_b;

};


#endif //CRYPTOGRAPHY_DIFFIE_HELLMAN_CHANNEL_HPP
