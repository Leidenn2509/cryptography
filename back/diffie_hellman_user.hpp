//
// Created by Alexander Raish on 26/09/2019.
//

#ifndef TEST_DIFFIE_HELLMAN_USER_HPP
#define TEST_DIFFIE_HELLMAN_USER_HPP


#include <cstdint>
#include <limits>
#include <cstdlib>
#include <ctime>
#include <random>
#include <map>

class diffie_hellman_user {
public:
    uint64_t m_p;
    uint64_t m_g;


    diffie_hellman_user(uint64_t p, uint64_t g, std::random_device seed = std::random_device());
    bool generate_session_private_key_with(const std::shared_ptr<diffie_hellman_user>& other);
    bool send(std::string message, const std::shared_ptr<diffie_hellman_user> to);
    uint64_t get_public_key() const;

private:
    std::map<std::shared_ptr<diffie_hellman_user>, uint64_t> connections;
    uint64_t m_private_key;
    uint64_t m_public_key;
};


#endif //TEST_DIFFIE_HELLMAN_USER_HPP
