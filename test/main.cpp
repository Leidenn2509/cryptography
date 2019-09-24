#include <iostream>
#include <cmath>

uint64_t fast_pow(uint64_t base, uint64_t power, uint64_t p = std::numeric_limits<uint64_t>::max()) {
    uint64_t res = 1;
    uint64_t t = std::log2(power) + 1;
    for(uint64_t i = 0; i < t; ++i) {
        if (power % 2 == 1) {
            res = std::fmod(res*base, p);
        }
        base = std::fmod(base*base, p);
        power = power / 2;
    }
    return res;
}

int main() {
    std::cout << fast_pow(2, 10, 10) << std::endl;
    std::cout << std::fmod(23, 20) << std::endl;
    return 0;
}