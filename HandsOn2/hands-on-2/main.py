data = [
    (23, 651),
    (26, 762),
    (30, 856),
    (34, 1063),
    (43, 1190),
    (48, 1298),
    (52, 1421),
    (57, 1440),
    (58, 1518),
]


def sum(data, callback):
    s = 0
    for (x, y) in data:
        s += callback(x, y)
    return s

def main():
    s1 = len(data) * sum(data, lambda x, y: x * y)
    s2 = sum(data, lambda x, _: x) * sum(data, lambda _, y: y)
    s3 = len(data) * sum(data, lambda x, _: x ** 2)
    s4 = sum(data, lambda x, _: x) * sum(data, lambda x, _: x)
    s5 = s1 - s2
    s6 = s3 - s4
    b1 = s5 / s6

    s7 = sum(data, lambda _, y: y)
    s8 = b1 * sum(data, lambda x, _: x)
    b0 = (s7 - s8) / len(data)

    print("b1 = {}".format(b1))
    print("b0 = {}".format(b0))

    r60 = b0 + (b1 * 60)
    r70 = b0 + (b1 * 70)
    r80 = b0 + (b1 * 80)
    print("When x = 60, y => {}".format(r60))
    print("When x = 70, y => {}".format(r70))
    print("When x = 80, y => {}".format(r80))

if __name__ == "__main__":
    main()

