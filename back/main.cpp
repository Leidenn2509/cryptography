#include <iostream>
#include <cmath>
#include "crypt_utils.hpp"
#include "diffie_hellman_user.hpp"

int main() {
    diffie_hellman_user alice(30803, 2);
    diffie_hellman_user bob(30803, 2);
    alice.generate_session_private_key_with(std::make_shared<diffie_hellman_user>(bob));
    bob.generate_session_private_key_with(std::make_shared<diffie_hellman_user>(alice));
    return 0;
}