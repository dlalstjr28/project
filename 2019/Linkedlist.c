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
int listindex = 0;

int main(int argc, char *argv[]) {

	if (argc < 2) {
		fprintf(stderr, "Give any string (without whitespeac) to store in the linked list.\nUsage: %s \
             iamacybersecuritygenius.",argv[0]);
			exit(1);
	}
	else if (argc > 2) {
		fprintf(stderr, "Give only one string (without whitespeac) to store in the linked list.\nUsage: %s \
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
	printf("\n")

	while (current != NULL) {
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
	printf("The length starting form current is %d\n", length(current));

	return 0;
}

int length(listPointer list) {
	int len = 1;
	while (list->link != NULL) {
		list->link = (list->link)->link;
		len++;
	}
	return len;
}
char readValue(int i) {



}
listPointer findNode(int i) {
	listPointer ptr1 = ptr;
	for (int j = 0; j < i;j++) {
		ptr1 = ptr1->link;
	}
	return ptr1;
}
	void insert(listPointer node, char value) 
	{
		listNode * node = (listNode *)malloc(sizeof(listNode));
		node->link = ptr->link;
		node->value = value;
		ptr->link = node;
		node->index = listindex;
		if (node == NULL) {
			node->link = ptr->link;
			ptr->link = node;
		}
		node = (ptr->link)->link;
		listindex++;
	}

	void delete(listPointer trail) {
		if (trail != NULL && trail->link == NULL) {
			free(trail);
		}
	}
	void printList(listPointer node) {
		for (int i = 0; i < length(node);i++) {
			printf("%c", node->value);
			node = node->link;
		}
	}
	int search(char _value) {
		int i = 0;
		for (int i = 0;i < length(ptr);i++) {
			if (ptr->value == _value) {
				i = ptr->index;
				break;
			}
			else {
				ptr = ptr->link;
				i = -1;
			}
			return i;
		}
	}
		listPointer invert(listPointer lead) {
			return lead;
		}
