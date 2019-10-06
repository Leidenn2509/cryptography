//
// Created by Alexander Raish on 26/09/2019.
//

#ifndef TEST_CRYPT_UTILS_HPP
#define TEST_CRYPT_UTILS_HPP

#include <cstdint>
#include <limits>


uint64_t fast_pow(uint64_t base, int64_t power, uint64_t p = std::numeric_limits<uint64_t>::max());
uint64_t gcd(uint64_t a, uint64_t b, int64_t *x = nullptr, int64_t *y = nullptr);
uint64_t inv(uint64_t m, uint64_t p);


#endif //TEST_CRYPT_UTILS_HPP
