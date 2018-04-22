package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IMediasceneDAO;
import com.example.kidsstories.Entities.Mediascene;
import org.springframework.stereotype.Repository;

@Repository
public class MediasceneDAO extends GenericDAO<Mediascene, Integer> implements IMediasceneDAO {
}
