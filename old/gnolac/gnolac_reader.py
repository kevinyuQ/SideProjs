def scheme_read(src):
    """Read the next expression from SRC, a Buffer of tokens.

    >>> scheme_read(Buffer(tokenize_lines(['nil'])))
    nil
    >>> scheme_read(Buffer(tokenize_lines(['1'])))
    1
    >>> scheme_read(Buffer(tokenize_lines(['true'])))
    True
    >>> scheme_read(Buffer(tokenize_lines(['(+ 1 2)'])))
    Pair('+', Pair(1, Pair(2, nil)))
    """
    if src.current() is None:
        raise EOFError
    val = src.remove_front() # Get the first token
    if val == 'nil':
        return nil
    elif val == '(':
        return read_tail(src)
    elif val == "'":
        return Pair('quote', Pair(scheme_read(src), nil))
    elif val not in DELIMITERS:
        return val
    else:
        raise SyntaxError('unexpected token: {0}'.format(val))
