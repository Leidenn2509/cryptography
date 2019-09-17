#include <iostream>
#include <cmath>

uint64_t fast_pow(uint64_t base, uint64_t x, uint64_t p = std::numeric_limits<uint64_t>::max()) {
    uint64_t res = 1;
    for(uint64_t i = 0; i < std::log2(x) + 1; ++i) {
        if (x%2 == 1) {
            res = std::fmod(res*base, p);
        }
        base = std::fmod(base*base, p);
        x = x/2;
    }
    return res;
}

int main() {
    uint64_t a = 5; // 101
    std::cout << fast_pow(2, 10, 10) << std::endl;
    std::cout << std::fmod(23, 20) << std::endl;
    return 0;
}