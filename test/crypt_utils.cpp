//
// Created by Alexander Raish on 30/09/2019.
//

#include "crypt_utils.hpp"

#include <cmath>
#include <array>

uint64_t fast_pow(uint64_t base, uint64_t power, uint64_t p) {
    uint64_t res = 1;
    uint64_t t = std::log2(power) + 1;
    for (uint64_t i = 0; i < t; ++i) {
        if (power % 2 == 1) {
            res = std::fmod(res * base, p);
        }
        base = std::fmod(base * base, p);
        power = power / 2;
    }
    return res;
}

uint64_t gcd(uint64_t a, uint64_t b, uint64_t *x, uint64_t *y) {
    std::array<uint64_t, 3> u{a, 1, 0};
    std::array<uint64_t, 3> v{b, 0, 1};
    std::array<uint64_t, 3> t{0, 0, 0};
    uint64_t q = 0;
    while (v[0] != 0) {
        q = u[0] / v[0];
        t = {u[0] % v[0], u[1] - q * v[1], u[2] - q * v[2]};
        u = v;
        v = t;
    }
    if (x != nullptr) {
        *x = u[1];
    }
    if (y != nullptr) {
        *y = u[2];
    }
    return u[0];
}