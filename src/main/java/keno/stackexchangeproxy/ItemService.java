package keno.stackexchangeproxy;

import keno.stackexchangeproxy.dto.AnswerStackDto;
import keno.stackexchangeproxy.dto.CommentStackDto;
import keno.stackexchangeproxy.dto.ItemDto;
import keno.stackexchangeproxy.dto.OwnerDto;
import keno.stackexchangeproxy.respository.AnswerStackRepository;
import keno.stackexchangeproxy.respository.CommentStackRepository;
import keno.stackexchangeproxy.respository.ItemRepository;
import keno.stackexchangeproxy.respository.OwnerRepository;
import keno.stackexchangeproxy.stackData.AnswerStack;
import keno.stackexchangeproxy.stackData.CommentStack;
import keno.stackexchangeproxy.stackData.Item;
import keno.stackexchangeproxy.stackData.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ItemService {

    @Autowired ItemRepository itemRepository;
    @Autowired OwnerRepository ownerRepository;
    @Autowired AnswerStackRepository answerStackRepository;
    @Autowired CommentStackRepository commentStackRepository;

    private int idx = 0;
    private ArrayList<Owner> owners = new ArrayList<>();

    @Transactional
    public void store(ItemDto itemDto) {
        // 1. save owners
        saveOwner(itemDto.getOwner());
        for (AnswerStackDto a : itemDto.getAnswers()) {
            saveOwner(a.getOwner());
            for (CommentStackDto c : a.getComments()) {
                saveOwner(c.getOwner());
            }
        }
        for (CommentStackDto c : itemDto.getComments()) {
            saveOwner(c.getOwner());
        }
        // 2. save item
        Item item = Item.createItem(itemDto, nextOwner());
        itemRepository.save(item);

        // 3. save answers 4. save comments
        for (AnswerStackDto a : itemDto.getAnswers()) {
            AnswerStack as = item.addAnswer(a, nextOwner());
            answerStackRepository.save(as);
            for (CommentStackDto ac : a.getComments()) {
                CommentStack acs = as.addComment(ac, nextOwner());
                commentStackRepository.save(acs);
            }
        }
        for (CommentStackDto c : itemDto.getComments()) {
            CommentStack cs = item.addComment(c, nextOwner());
            commentStackRepository.save(cs);
        }
    }

    @Transactional
    public void saveOwner(OwnerDto dto) {
        Owner owner = Owner.createOwner(dto);
        ownerRepository.save(owner);
        owners.add(owner);
    }

    public Owner nextOwner() {
        return owners.get(idx++);
    }
}
