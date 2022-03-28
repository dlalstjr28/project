#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct _listNode * listPointer;
typedef struct _listNode {
	int index;
	char value;
	listPointer link;
} listNode;

int length(listPointer list);
char readValue(int i);
listPointer findNode(int i);
void insert(listPointer node, char value);
void delete(listPointer trail);
void printList(listPointer node);
int search(char _value);
listPointer invert(listPointer lead);

listPointer ptr = NULL;

int main(int argc, char *argv[]) {

	if (argc < 2) {
		fprintf(stderr, "Give any string (without whitespace) to store in the linked list.\nUsage: %s \
             iamacybersecuritygenius.",argv[0]);
			exit(1);
	}
	else if (argc > 2) {
		fprintf(stderr, "Give only one string (without whitespace) to store in the linked list.\nUsage: %s \
			iamacybersecuritygenius.",argv[0]);
			exit(1);
	}

	int len = strlen(argv[1]);

	for (int i = 0; i < len; i++) {
		insert(NULL, argv[1][i]);
	}
	ptr = invert(ptr);
	listPointer current = ptr;
	printList(current);
	  printf("\n");
		
	for (int i = 0; i < length(current); i += 2) {
		printf("%c", readValue(i)); // imcbreuiyeis
	}
	printf("\n");
	
	while(current != NULL) {
	   listPointer next = current->link;
			if (next) {
				delete(current);
				current = current->link;
			}
			else //next is null
				current = next;
		}
	printList(ptr); // imcbreuiyeis
	printf("\n");

	current = findNode(search('r'));
	printList(current); //reuiyeis
	printf("\n");
	printf("The length starting from current is %d\n", length(current));

	return 0;
}

int length(listPointer list) {
	int len = 1;
	while (list->link != NULL) {
		list = list->link;
		len++;
	}
	return len;
}
char readValue(int i) {
	char c = 0;
	listPointer ptr1 = ptr;
	while (ptr1!= NULL) {
		if (ptr1->index == i) {
			c = ptr1->value;
			break;
		}
		else
		    ptr1 = ptr1->link;
	}
	return c;
}
    listPointer findNode(int i) {
	listPointer ptr1 = ptr;
	while (ptr1->index != i) {
		ptr1 = ptr1->link;
	}
	return ptr1;
}
	void insert(listPointer node, char value) {
		listPointer temp = NULL;
		listPointer newNode = (listPointer)malloc(sizeof(listNode));
		newNode->value = value;
		newNode->link = NULL;
		newNode->index = 0;

		if (node == NULL) {
			newNode->link = ptr;
			ptr = newNode;
		}
		else {
			newNode->index = node->index + 1;
			newNode->link = node->link;
			node->link = newNode;
		}
		temp = newNode->link;
		while (temp) {
			temp->index++;
			temp = temp->link;
		}
	}

	void delete(listPointer trail) {
			listPointer getnext = trail->link;
			listPointer temp = getnext->link;
			trail->link = temp;
			free(getnext);
	}
	void printList(listPointer node) {
		while (node!=NULL) {
			printf("%c",node->value);
			node = node->link;
		}
		
	}
	int search(char _value) {
		int i = 0;
		listPointer ptr1 = ptr;
		while (ptr1->link!=NULL) {
			ptr1 = ptr1->link;
			if (ptr1->value == _value) {
				i = ptr1->index;
				break;
			}
		}
		return i;
	}
	listPointer invert(listPointer lead) {
			listPointer ptr1 = ptr;
			while ((ptr1)!=(lead)) {
			     ptr1 = ptr1->link;
			}
			return ptr1;
		}
