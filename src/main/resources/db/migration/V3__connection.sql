alter table players
    add constraint FK5nglidr00c4dyybl171v6kask
        foreign key (team_id)
            references teams (id);