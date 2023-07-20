package kz.greetgo.greetgo.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Filter {

    private Long limit;
    private Long offset;
}
